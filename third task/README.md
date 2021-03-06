1) Разработать для своего варианта структуру XML документа, описать её с помощью XSD. Создать файл XML, соответствующий разработанной XSD схеме.
2) При разработке XSD использовать простые и комплексные типы, перечисления, шаблоны и предельные значения, обязательно использование атрибутов и типа ID.
3) Создать Java-класс(классы), соответствующие разработанной схеме.
4) Создать Java-приложение для разбора XML-документа и инициализации коллекции объектов информацией из XML-файла. Для разбора использовать SAX, DOM или StAX парсер. Для сортировки объектов использовать интерфейс Comparator.
5) Произвести проверку корректности и валидности XML-документа с привлечением XSD.

**Лекарственные препараты.**

Лекарственные препараты имеют следующие характеристики.

  Name – наименование препарата. 
  
  Pharm – фирма-производитель.
  
  Group – группа препаратов к которым относится лекарство (антибиотики, болеутоляющие, витамины и т.п.). 
  
  Analogs (может быть несколько) – содержит наименование аналога. 
  
  Versions – варианты исполнения (консистенция/вид: таблетки, капсулы, порошок, капли и т.п.).
  Для каждого варианта исполнения может быть несколько производителей лекарственных препаратов со следующими характеристиками: 
  
  Certificate – свидетельство о регистрации препарата (номер, даты выдачи/истечения действия, регистрирующая организация); 
  
  Package – упаковка (тип упаковки, количество в упаковке, цена за упаковку); 
  
  Dosage – дозировка препарата, периодичность приема. 
  
