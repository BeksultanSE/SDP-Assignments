interface UIFactory{
    Button createButton();
    TextField createTextField();
}
class DarkThemeFactory implements UIFactory{

    public Button createButton(){
        return new DarkThemeButton();
    }
    public TextField createTextField() {
        return new DarkThemeTextField();
    }
}

class LightThemeFactory implements UIFactory{
    public Button createButton(){
        return new LightThemeButton();
    }
    public TextField createTextField(){
        return new LightThemeTextField();
    }
}

interface Button{

    void render();
}
interface TextField{
    void render();

}
class DarkThemeButton implements Button{
    public void render(){
        System.out.println("Dark Theme Button");
    }

}
class LightThemeButton implements Button{
    public void render(){
        System.out.println("Light Theme Button");
    }

}
class LightThemeTextField implements TextField{
    public void render(){
        System.out.println("Light Theme TextField");
    }

}
class DarkThemeTextField implements TextField{

    public void render(){
        System.out.println("Dark Theme TextField");
    }
}
