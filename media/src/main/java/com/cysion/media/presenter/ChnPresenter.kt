package com.cysion.media.presenter

import com.cysion.ktbox.base.BasePresenter
import com.cysion.media.net.BaseRespRx
import com.cysion.media.net.ChannelCaller
import com.cysion.media.ui.iview.ChnView
import com.cysion.other.addTo
import com.cysion.targetfun._subscribe

class ChnPresenter : BasePresenter<ChnView>() {

    fun getChnList() {
        checkViewAttached()
        attchedView?.loading()
        ChannelCaller.api.getChannelList()
            .compose(BaseRespRx.validateToMain())
            ._subscribe {
                _onNext {
                    attchedView?.apply {
                        setChannels(it[0].channellist)
                    }
                }
                _onError {
                    attchedView?.stopLoad()
                    error(it)
                }
            }.addTo(compositeDisposable)
    }

}