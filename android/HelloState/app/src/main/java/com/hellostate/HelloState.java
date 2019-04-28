package com.hellostate;

import android.os.Message;

/**
 * Created by android on 17-7-14.
 */

public class HelloState extends StateMachine {


    public HelloState(String name) {
        super(name);
        log("ctor E");

        // Add states, use indentation to show hierarchy
        addState(mP1);
        addState(mS1, mP1);
        addState(mS2, mP1);
        addState(mP2);
        //addState(mS1, mP2);
        addState(mS3, mP3);

        // Set the initial state
        setInitialState(mS1);
        log("ctor X");
    }


    public static final int CMD_1 = 1;
    public static final int CMD_2 = 2;
    public static final int CMD_3 = 3;
    public static final int CMD_4 = 4;
    public static final int CMD_5 = 5;

    P1 mP1 = new P1();
    S1 mS1 = new S1();
    S2 mS2 = new S2();
    P2 mP2 = new P2();
    P3 mP3 = new P3();
    S3 mS3 = new S3();



    class P1 extends State {
        @Override
        public void enter() {
            log("mP1.enter");
        }
        @Override
        public boolean processMessage(Message message) {
            boolean retVal;
            log("mP1.processMessage what=" + message.what);
            switch(message.what) {
                case CMD_2:
                    // CMD_2 will arrive in mS2 before CMD_3
                    //CMD_3没有发送出去，并且会在CMD_2之后发送
                    sendMessage(obtainMessage(CMD_3));
                    deferMessage(message);
                    transitionTo(mS2);
                    retVal = IState.HANDLED;
                    break;
                default:
                    // Any message we don't understand in this state invokes unhandledMessage
                    retVal = IState.NOT_HANDLED;
                    break;
            }
            return retVal;
        }
        @Override
        public void exit() {
            log("mP1.exit");
        }
    }

    class S1 extends State {
        @Override
        public void enter() {
            log("mS1.enter");
        }
        @Override
        public boolean processMessage(Message message) {
            log("S1.processMessage what=" + message.what);
            if (message.what == CMD_1) {
                // Transition to ourself to show that enter/exit is called
                //其实这个方法并不会开始切换，只是说下一次的切换
                transitionTo(mS1);
                //返回这个值之后才会开始进行切换
                return IState.HANDLED;
            } else {
                // Let parent process all other messages
                return IState.NOT_HANDLED;
            }
        }
        @Override
        public void exit() {
            log("mS1.exit");
        }
    }

    class S2 extends State {
        @Override
        public void enter() {
            log("mS2.enter");
        }
        @Override
        public boolean processMessage(Message message) {
            boolean retVal;
            log("mS2.processMessage what=" + message.what);
            switch(message.what) {
                case(CMD_2):
                    //先处理CMD_2,此时CMD_4也没有发送
                    //因为没有状态切换，所以CMD_3还是在mS2中处理
                    sendMessage(obtainMessage(CMD_4));
                    retVal = IState.HANDLED;
                    break;
                case(CMD_3):
                    //延迟CMD_3, 即CMD_3要比CMD_4更早处理
                    deferMessage(message);
                    //切换到mP2, CMD_3和CMD_4都在mP2处理
                    transitionTo(mP2);
                    retVal = IState.HANDLED;
                    break;
                default:
                    retVal = IState.NOT_HANDLED;
                    break;
            }
            return retVal;
        }
        @Override
        public void exit() {
            log("mS2.exit");
        }
    }

    class P2 extends State {
        @Override
        public void enter() {
            log("mP2.enter");
            //CMD_5并不会立即发送
            sendMessage(obtainMessage(CMD_5));
        }
        @Override
        public boolean processMessage(Message message) {
            log("P2.processMessage what=" + message.what);
            switch(message.what) {
                case(CMD_3):
                    //先处理
                    break;
                case(CMD_4):
                    //其次处理
                    break;
                case(CMD_5):
                    //最后处理
                    transitionToHaltingState();
                    break;
            }
            return IState.HANDLED;
        }
        @Override
        public void exit() {
            log("mP2.exit");
        }
    }

    class P3 extends State {
        @Override
        public void enter() {
            log("mP3.enter");
            sendMessage(obtainMessage(CMD_5));
        }
        @Override
        public boolean processMessage(Message message) {
            log("P3.processMessage what=" + message.what);
            return IState.HANDLED;
        }

        @Override
        public void exit() {
            log("mP3.exit");
        }
    }

    class S3 extends State {
        @Override
        public void enter() {
            log("mS3.enter");
            sendMessage(obtainMessage(CMD_5));
        }
        @Override
        public boolean processMessage(Message message) {
            log("S3.processMessage what=" + message.what);
            return IState.HANDLED;
        }
        @Override
        public void exit() {
            log("mS3.exit");
        }
    }

    @Override
    protected void onHalting() {
        log("halting");
        synchronized (this) {
            this.notifyAll();
        }
    }


}

