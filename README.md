# VKTest
VK API test

Просто проба VK SDK 
- Пытается поднять сессию
- Если сессия не восстанавливается - перекидывает на страницу авторизации
- После успешной авторизации загружает в RecyclerView, в CardView всех друзей авторизовавшегося пользователя с фотографией и краткой информацией (имя и город). При неудачной авторизации(или отказе пользователя дать доступ приложению), оповещает пользователя, что необходимо авторизовать для продолжения работы
- Есть возможность раздогиниться по требованию пользователя.

Больше пока ничего не делает, целью было попробовать авторизацию oauth2 и API какой-нибудь соц сети, выбрал Vkontakte. 
Если придумаю что-нибудь интересное - доделаю функционал.
