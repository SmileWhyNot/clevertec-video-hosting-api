### Clevertec Video Hosting API

Добро пожаловать в CleverTec Video Hosting API! Этот API предоставляет возможность управления пользователями, каналами и их подписками.

#### Технологии

- Java 17
- Spring Boot 3.0+
- Gradle
- PostgreSQL
- JPA
- Spring Data Repositories
- Lombok
- MapStruct
- Java Streams

#### Функционал

1. **Управление пользователями**

    - **Регистрация пользователя**

      ```
      POST /api/v1/person/register
      ```

      Пример тела запроса:
      ```json
      {
          "nickname": "person1",
          "name": "person1",
          "email": "person1@mail.ru",
          "password": "person1"
      }
      ```

    - **Обновление информации о пользователе**
      (перадаются только необходимые для обновления поля)
      ```
      PUT /api/v1/person/{id}
      ```

      Пример тела запроса:
      ```json
      {
          "nickname": "person1_1",
          "name": "person1_1",
          "email": "person1_1@mail.ru",
          "password": "person1_1"
      }
      ```

    - **Удаление пользователя**

      ```
      DELETE /api/v1/person/{id}
      ```

    - **Получение списка подписок пользователя**

      ```
      GET /api/v1/person/{id}/subscriptions
      ```

2. **Управление каналами**

    - **Создание канала**

      ```
      POST /api/v1/channel
      ```

      Пример тела запроса **`ключ channel`** (аватарка прилагается дополнительно к запросу с `ключом avatar`):
      ```json
      {
          "name": "channel1",
          "description": "channel1 description",
          "creationDate": "2023-11-29T15:30:00+03:00",
          "author": {
              "email": "person1@mail.ru"
          },
          "lang": "ru",
          "category": "News"
      }
      ```

    - **Получение подробной информации о канале**

      ```
      GET /api/v1/channel/{id}
      ```

    - **Обновление информации о канале**
      (перадаются только необходимые для обновления поля)
      ```
      PUT /api/v1/channel/{id}
      ```

      Пример тела запроса:
      ```json
      {
          "description": "channel for delete request"
      }
      ```

    - **Удаление канала**

      ```
      DELETE /api/v1/channel/{id}
      ```

    - **Получение списка каналов с фильтрацией**

      ```
      GET /api/v1/channel?lang=RU&category=Funny&page=0&size=20
      ```

3. **Управление подписками**

    - **Подписка на канал**

      ```
      POST /api/v1/subscriptions/subscribe
      ```

      Пример тела запроса:
      ```json
      {
          "personId": "4",
          "channelId": "1"
      }
      ```

    - **Отписка от канала**

      ```
      POST /api/v1/subscriptions/unsubscribe
      ```

      Пример тела запроса:
      ```json
      {
          "personId": "2",
          "channelId": "1"
      }
      ```

#### Примеры Postman

В репозитории предоставлен файл коллекции Postman (`clevertec-video-hosting.postman_collection.json`), который содержит примеры запросов для удобного тестирования API.

**Примечание**: Функционал регистрации условный, подразумевает создание нового пользователя. Отображение реальной регистрации пользователя не реализовано в данной версии API.

### Запуск приложения

Для успешного запуска приложения выполните следующие шаги:

1. **Настройка базы данных**

    - Убедитесь, что PostgreSQL установлен на вашем сервере.
    - Создайте базу данных для приложения, `например video-hosting`.

2. **Конфигурация приложения**

    - Отредактируйте файл `application.yml` в ресурсах приложения для задания параметров базы данных.

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/video-hosting
   spring.datasource.username=username
   spring.datasource.password=password
   ```

3. **Запуск приложения**

    - Запустите приложение, используя вашу IDE или командную строку.

4. **Использование API**

    - Используйте предоставленные примеры Postman для тестирования функционала API.