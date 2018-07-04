package com.game.kernel.dispatch;

import com.game.msg.IMessageReadable;

public interface IDispatch {

    void dispatch(final Object groupInfo,final IMessageReadable msg);
}
