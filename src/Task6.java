import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class Task6 {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();
        TextEditor editor = new TextEditor(factory);

        editor.insertCharacter('H', "Times New Roman", 10, 0, 0);
        editor.insertCharacter('e', "Times New Roman", 10, 1, 0);
        editor.insertCharacter('l', "Times New Roman", 10, 2, 0);
        editor.insertCharacter('l', "Times New Roman", 10, 3, 0);
        editor.insertCharacter('o', "Times New Roman", 10, 4, 0);
        editor.insertCharacter('!', "Times New Roman", 10, 5, 0);

        editor.renderDocument();

        System.out.println("\nUnique character objects created: " + factory.getCharacterPoolSize());
    }
}

class Character {
    private char value;
    private String font;
    private int size;

    public Character(char value, String font, int size) {
        this.value = value;
        this.font = font;
        this.size = size;
    }

    public void render(int x, int y) {
        System.out.println("Rendering character '" + value + "' at position (" + x + ", " + y + ") with font " + font + " and size " + size);
    }
}


class CharacterFactory {
    private Map<String, Character> characterPool = new HashMap<>();

    public Character getCharacter(char value, String font, int size) {
        String key = value + font + size;
        Character character = characterPool.get(key);

        if (character == null) {
            character = new Character(value, font, size);
            characterPool.put(key, character);
        }
        return character;
    }

    public int getCharacterPoolSize() {
        return characterPool.size();
    }
}


class TextEditor {
    private List<CharacterPosition> characters = new ArrayList<>();
    private CharacterFactory characterFactory;

    public TextEditor(CharacterFactory characterFactory) {
        this.characterFactory = characterFactory;
    }

    private class CharacterPosition {
        Character character;
        int x;
        int y;

        public CharacterPosition(Character character, int x, int y) {
            this.character = character;
            this.x = x;
            this.y = y;
        }
    }

    public void insertCharacter(char value, String font, int size, int x, int y) {
        Character character = characterFactory.getCharacter(value, font, size);
        characters.add(new CharacterPosition(character, x, y));
    }

    public void renderDocument() {
        for (CharacterPosition cp : characters) {
            cp.character.render(cp.x, cp.y);
        }
    }
}
