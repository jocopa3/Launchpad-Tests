/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jocopa3.macropad;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.MouseEvent.*;
/**
 *
 * @author Matt
 */
public class ActionEnums {
    
    public static final int KEYBOARD_KEYS[] = {
        // alphabetical
        VK_A, VK_B, VK_C, VK_D, VK_E, VK_F, VK_G, VK_H, VK_I, VK_J, VK_K, VK_L, VK_M, VK_N, VK_O, VK_P, VK_Q, VK_R, VK_S, VK_T, VK_U, VK_V, VK_W, VK_X, VK_Y, VK_Z, -1,

        // numeric
        VK_0, VK_1, VK_2, VK_3, VK_4, VK_5, VK_6, VK_7, VK_8, VK_9, -1,

        // punctuation
        VK_COMMA, VK_PERIOD, VK_SLASH, VK_SEMICOLON, VK_EQUALS, VK_OPEN_BRACKET, VK_BACK_SLASH, VK_CLOSE_BRACKET, -1,

        // symbols
        VK_AMPERSAND, VK_ASTERISK, VK_QUOTEDBL, VK_LESS, VK_GREATER, VK_BRACELEFT, VK_BRACERIGHT, VK_AT, VK_COLON, VK_CIRCUMFLEX, VK_DOLLAR, VK_EURO_SIGN, VK_EXCLAMATION_MARK, VK_INVERTED_EXCLAMATION_MARK, VK_LEFT_PARENTHESIS, VK_NUMBER_SIGN, VK_MINUS, VK_PLUS, VK_RIGHT_PARENTHESIS, VK_UNDERSCORE, -1,

        // special keys
        VK_ENTER, VK_BACK_SPACE, VK_TAB, VK_CANCEL, VK_CLEAR, VK_COMPOSE, VK_PAUSE, VK_CAPS_LOCK, VK_ESCAPE, VK_SPACE, VK_PAGE_UP, VK_PAGE_DOWN, VK_END, VK_HOME, VK_LEFT, VK_UP, VK_RIGHT, VK_DOWN, VK_BEGIN, -1,

        // modifiers
        VK_SHIFT, VK_CONTROL, VK_ALT, VK_META, VK_ALT_GRAPH, -1,

        // math keys
        VK_MULTIPLY, VK_ADD, VK_SEPARATOR, VK_SUBTRACT, VK_DECIMAL, VK_DIVIDE, VK_DELETE, VK_NUM_LOCK, VK_SCROLL_LOCK, -1,

        // OS keys
        VK_WINDOWS, VK_CONTEXT_MENU, -1,

        // function keys
        VK_F1, VK_F2, VK_F3, VK_F4, VK_F5, VK_F6, VK_F7, VK_F8, VK_F9, VK_F10, VK_F11, VK_F12, VK_F13, VK_F14, VK_F15, VK_F16, VK_F17, VK_F18, VK_F19, VK_F20, VK_F21, VK_F22, VK_F23, VK_F24, -1,

        // events
        VK_PRINTSCREEN, VK_INSERT, VK_HELP, VK_BACK_QUOTE, VK_QUOTE, -1,

        // directional
        VK_KP_UP, VK_KP_DOWN, VK_KP_LEFT, VK_KP_RIGHT, -1,

        // language
        VK_DEAD_GRAVE, VK_DEAD_ACUTE, VK_DEAD_CIRCUMFLEX, VK_DEAD_TILDE, VK_DEAD_MACRON, VK_DEAD_BREVE, VK_DEAD_ABOVEDOT, VK_DEAD_DIAERESIS, VK_DEAD_ABOVERING, VK_DEAD_DOUBLEACUTE, VK_DEAD_CARON, VK_DEAD_CEDILLA, VK_DEAD_OGONEK, VK_DEAD_IOTA, VK_DEAD_VOICED_SOUND, VK_DEAD_SEMIVOICED_SOUND, -1,

        // japanese
        VK_FINAL, VK_CONVERT, VK_NONCONVERT, VK_ACCEPT, VK_MODECHANGE, VK_KANA, VK_KANJI, VK_ALPHANUMERIC, VK_KATAKANA, VK_HIRAGANA, VK_FULL_WIDTH, VK_HALF_WIDTH, VK_ROMAN_CHARACTERS, VK_ALL_CANDIDATES, VK_PREVIOUS_CANDIDATE, VK_CODE_INPUT, VK_JAPANESE_KATAKANA, VK_JAPANESE_HIRAGANA, VK_JAPANESE_ROMAN, VK_KANA_LOCK, VK_INPUT_METHOD_ON_OFF, -1,

        // sun keyboard actions
        VK_AGAIN, VK_UNDO, VK_COPY, VK_PASTE, VK_CUT, VK_FIND, VK_PROPS, VK_STOP
    };
    
    public static final String KEYBOARD_KEY_NAMES[] = new String[KEYBOARD_KEYS.length];
    
    static {
        for(int i = 0; i < KEYBOARD_KEYS.length; i++) {
            if(KEYBOARD_KEYS[i] == -1)
                KEYBOARD_KEY_NAMES[i] = "――――――――";
            else
                KEYBOARD_KEY_NAMES[i] = KeyEvent.getKeyText(KEYBOARD_KEYS[i]);
        }
    }
    
    public static final int KEY_MASK = CHAR_UNDEFINED;
    
    public static final int KEY_EVENT_MASK = 0x30000;
    
    public static final int NONE = 0x0;
    public static final int PRESSED = 0x10000;
    public static final int RELEASED = 0x20000;
    public static final int TYPED = 0x30000;
    
    public static final int MOUSE_MOVE_X_MASK = 0xFFFF;
    public static final int MOUSE_MOVE_Y_MASK = 0xFFFF0000;
    public static final int MOUSE_MOVE_Y_OFFSET = 16;
}
