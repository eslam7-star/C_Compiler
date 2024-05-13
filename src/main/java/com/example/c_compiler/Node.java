package com.example.c_compiler;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private List<Node> children;
    private Tokenn token;

    private String str;

    public Node(Tokenn token) {
        this.token = token;
        this.children = new ArrayList<>();
    }

    public Node(String token_str) {
        this.str = token_str;
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
