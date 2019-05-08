#language:ru
# encoding: utf-8

Функционал: Yandex.Market

  Предыстория:Переходим в Яндекс.Маркет
    Когда совершен переход на страницу "Главная страница Яндекс" по ссылке "https://yandex.ru/"
    И в списке "Меню" выбран элемент с текстом "Маркет"
    И переход на страницу "Главная страница Яндекс.Маркет"

  Структура сценария: Проверка названия товара
    Когда выполнено нажатие на кнопку "Электроника" в списке "Меню"
    Тогда переход на страницу "Страница каталога Яндекс.Маркет"
    Когда в списке "Каталог" выбран элемент с текстом "<category>"
    Тогда переход на страницу "Страница результата Яндекс.Маркет"
    Когда в списке "Фильтры.Производитель" выбран элемент с текстом "<type>"
    И в поле "Фильтры.Цена от" введено значение "<priceFrom>"
    И в поле "Фильтры.Цена до" введено значение "<priceTo>"
    Когда в блоке "Результат" под номером "1" найден элемент "Результат.Название" и сохранен текст в переменную "<productTitle1>"
    И в блоке "Результат" под номером "1" выбран элемент "Результат.Название"
    Тогда переход на страницу "Страница товара Яндекс.Маркет"
    Когда значение поля "Название" сохранено в переменную "<productTitle2>"
    Тогда сравнение значений переменных "<productTitle1>" и "<productTitle2>"

    Примеры:
      | category                       | type    | priceFrom | priceTo |
      | Мобильные телефоны             | Samsung | 40000     |         |
      | Наушники и Bluetooth-гарнитуры | Beats   | 17000     | 25000   |