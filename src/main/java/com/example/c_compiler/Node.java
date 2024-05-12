package com.example.c_compiler;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    List<Node> children;

    Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    void addChild(Node child) {
        children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }
    // Other methods...
}
