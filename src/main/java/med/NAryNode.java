package med;

import java.util.List;

/**
 * Created by udaythota on 11/9/19.
 * Basic N-Ary Node structure
 */
public class NAryNode {
    int val;
    List<NAryNode> children;

    public NAryNode() {
    }

    public NAryNode(int _val, List<NAryNode> _children) {
        val = _val;
        children = _children;
    }
}