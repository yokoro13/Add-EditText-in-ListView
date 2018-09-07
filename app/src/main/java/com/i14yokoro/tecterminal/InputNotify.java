package com.i14yokoro.tecterminal;


/**
 * 入力されたことをActivityに通知する
 * コマンド等を追加する場合はこことInputListenerに追加しそれぞれの処理を書いていく
 *
 */
public class InputNotify {

    private InputListener listener;

    //Enterが入力されたことを通知
    public void notifyInputEnter(){
        listener.inputEnter();
    }

    //Clearコマンドが入力されたことを通知
    public void notifyInputClear(){
        listener.inputClearCommand();
    }

    //リスナーセット
    public void setListener(InputListener listener){
        this.listener = listener;
    }

}
