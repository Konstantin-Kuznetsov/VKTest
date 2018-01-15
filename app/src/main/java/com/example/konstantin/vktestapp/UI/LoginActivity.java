package com.example.konstantin.vktestapp.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.konstantin.vktestapp.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "VKTestApp";
    private Intent openWorkActivity;

    // https://vk.com/dev/permissions
    // список запрашиваемых разрешений для токена
    private static final String[] scope = new String[]{
            VKScope.FRIENDS,
            VKScope.WALL,
            VKScope.PHOTOS,
            VKScope.VIDEO,
            VKScope.AUDIO,
            VKScope.MESSAGES,
            VKScope.DOCS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        получение fingerprints приложения
//        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//        for (String str: fingerprints) {
//            Log.i("VK fingerprints list", str);
//        }

        // переход на рабочую активити
        openWorkActivity = new Intent(LoginActivity.this, WorkActivity.class);
        // убираем служебную активити с логином из бэкстэка
        openWorkActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Boolean shouldLogout = getIntent().getBooleanExtra("shouldLogout", false);

        if (shouldLogout) {
            VKSdk.logout();
            Toast.makeText(this, "Logged out!", Toast.LENGTH_LONG).show();
        } else {
            // проверка залогинен ли пользователь, и, если да - сразу переход на рабочую активити
            if (VKSdk.wakeUpSession(this)) startActivity(openWorkActivity);
        }

        // кнопка для старта процесса авторизации
        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener((View v) -> VKSdk.login(this, scope));
    }

    // обработка результатов автотризации приложения пользователем
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                Log.i(TAG, "Пользователь успешно авторизовался");
                // после авторизаци переходим на рабочую активити
                startActivity(openWorkActivity);

                // токен в лог
                Log.i(TAG, VKAccessToken.currentToken().accessToken);
            }
            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Log.i(TAG, "Произошла ошибка авторизации (например, пользователь запретил авторизацию)");
                Toast.makeText(LoginActivity.this, "Для продолжения работы \n необходима авторизация", Toast.LENGTH_SHORT).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
