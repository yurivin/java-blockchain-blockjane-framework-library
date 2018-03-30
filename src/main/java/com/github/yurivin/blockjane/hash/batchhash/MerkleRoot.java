package com.github.yurivin.blockjane.hash.batchhash;

import com.github.yurivin.blockjane.infrastracture.Environment;

import java.util.ArrayList;
import java.util.List;

public class MerkleRoot implements iBatchHash {

    public MerkleRoot(Environment env) {
        this.env = env;
    }

    private final Environment env;
    @Override
    public String apply(List<String> hashes) {
        return null;
    }

    public String getMerkleRoot(List<String> previousTreeLayer) {
        int count = previousTreeLayer.size();

        List<String> treeLayer = previousTreeLayer;
        while(count > 1) {
            treeLayer = new ArrayList<String>();
            for(int i=1; i < previousTreeLayer.size(); i++) {
                treeLayer.add(env.hashAlgo.apply(previousTreeLayer.get(i-1) + previousTreeLayer.get(i)));
            }
            count = treeLayer.size();
            previousTreeLayer = treeLayer;
        }
        String merkleRoot = (treeLayer.size() == 1) ? treeLayer.get(0) : "";
        return merkleRoot;
    }
}
