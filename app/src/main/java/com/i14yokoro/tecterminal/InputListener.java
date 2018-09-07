package com.i14yokoro.tecterminal;

import java.util.EventListener;

/**
 * 入力によるイベントリスナーはここに記述
 */
public interface InputListener extends EventListener{

    public void inputEnter();

    public void inputClearCommand();
}
