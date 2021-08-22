package prog02;

public enum Command {
    END("выход"),
    PRINT_PEOPLE("распечатать общий список людей"),
    PRINT_AVERAGE_AGE("распечатать средний возраст"),
    PRINT_PEOPLES_BY_MAIL("люди с почтой"),
    PRINT_WANT_WOMAN(String.format("сотрудницы %d-%d лет из Украины", Prog.WOMAN_AGE_FROM, Prog.WOMAN_AGE_TO)),
    ADD("добавить запись"),
    DEL("удалить запись"),
    ;


    private String nameRus;

    Command( String nameRus) {
        this.nameRus = nameRus;
    }

    public String getKey() {
        return Integer.toString(ordinal());
    }

    public String getNameRus() {
        return nameRus;
    }
}
