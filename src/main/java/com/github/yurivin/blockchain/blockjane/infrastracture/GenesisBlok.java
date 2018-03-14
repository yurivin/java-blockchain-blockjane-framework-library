package com.github.yurivin.blockchain.blockjane.infrastracture;

import java.util.Date;

public class GenesisBlok extends Block {

    public GenesisBlok(Environment env, String data, String genesisHash) {
        super(env, data, genesisHash);
    }

}
