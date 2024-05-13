package com.example.c_compiler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.abego.treelayout.NodeExtentProvider;
import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeLayoutExample  {

    ParseTree tree;

    TreeNode root_node;

    public TreeLayoutExample(ParseTree tree) {
        this.tree = tree;
    }

    private class TreeNode implements TreeForTreeLayout<TreeNode> {
        String name;
        List<TreeNode> children;

        TreeNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        void addChild(TreeNode child) {
            this.children.add(child);
        }


        @Override
        public TreeNode getRoot() {
            return root_node;
        }

        @Override
        public boolean isLeaf(TreeNode treeNode) {
            return treeNode.children.isEmpty();
        }

        @Override
        public boolean isChildOfParent(TreeNode treeNode, TreeNode parentNode) {
            return parentNode.children.contains(treeNode);
        }

        @Override
        public Iterable<TreeNode> getChildren(TreeNode parentNode) {
            return parentNode.children;
        }


        @Override
        public Iterable<TreeNode> getChildrenReverse(TreeNode parentNode) {
            List<TreeNode> reversedChildren = new ArrayList<>(parentNode.children);
            Collections.reverse(reversedChildren);
            return reversedChildren;
        }

        @Override
        public TreeNode getFirstChild(TreeNode parentNode) {
            return parentNode.children.isEmpty() ? null : parentNode.children.get(0);
        }

        @Override
        public TreeNode getLastChild(TreeNode parentNode) {
            return parentNode.children.isEmpty() ? null : parentNode.children.get(parentNode.children.size() - 1);
        }

    }


    private TreeNode convertParseTreeToTreeNode(ParseTree parseTree) {
        String name;
        if (parseTree instanceof TerminalNode) {
            name = ((TerminalNode) parseTree).getSymbol().getText();
        } else {
            name = parseTree.getClass().getSimpleName().replace("Context", "");
        }

        TreeNode node = new TreeNode(name);

        for (int i = 0; i < parseTree.getChildCount(); i++) {
            node.addChild(convertParseTreeToTreeNode(parseTree.getChild(i)));
        }

        return node;
    }



    public void displayParseTree() {
        TreeNode root = convertParseTreeToTreeNode(tree);
        root_node = root;

        NodeExtentProvider<TreeNode> nodeExtentProvider = new NodeExtentProvider<TreeNode>() {
            @Override
            public double getWidth(TreeNode treeNode) {
                return 60;
            }

            @Override
            public double getHeight(TreeNode treeNode) {
                return 20;
            }
        };

        DefaultConfiguration<TreeNode> configuration = new DefaultConfiguration<>(100, 50); // Increased gap

        TreeLayout<TreeNode> treeLayout = new TreeLayout<>(root, nodeExtentProvider, configuration);

        Pane pane = new Pane();
        for (TreeNode node : treeLayout.getNodeBounds().keySet()) {
            Rectangle2D.Double box = treeLayout.getNodeBounds().get(node);
            Text text = new Text(box.x, box.y, node.name);
            pane.getChildren().add(text);

            for (TreeNode child : node.children) {
                Rectangle2D.Double childBox = treeLayout.getNodeBounds().get(child);
                Line line = new Line(box.getCenterX(), box.getMaxY(), childBox.getCenterX(), childBox.getMinY());
                pane.getChildren().add(line);
            }
        }

        // Add zoom in/out functionality
        pane.setOnScroll(event -> {
            event.consume();

            double scaleFactor = (event.getDeltaY() > 0) ? 1.1 : 1/1.1;

            pane.setScaleX(pane.getScaleX() * scaleFactor);
            pane.setScaleY(pane.getScaleY() * scaleFactor);
        });

        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        // Add the pane to the ScrollPane
        scrollPane.setContent(pane);

        // Create a new Stage for the new window
        Stage newWindow = new Stage();
        newWindow.setTitle("Parse Tree");
        // Set the scene to include the scrollPane
        newWindow.setScene(new Scene(scrollPane, 800, 600));
        newWindow.show();
    }


}
