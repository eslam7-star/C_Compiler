package com.example.c_compiler;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private List<Node> children;
    private Token token;

    public Node(Token token) {
        this.token = token;
        this.children = new ArrayList<>();
    }

    public Node getParent() {
        return parent;
    }

    public void addChild(Node child) {
        child.parent = this;
        this.children.add(child);
    }

    // Other methods...
}
