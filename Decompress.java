package com.example.vision.myapplication;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Stack;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;


public class Decompress {

    private int i = 0;
    private FileInputStream fis;
    private StringBuilder sb = new StringBuilder();
    private String smsText = "C:\\Users\\vision\\Desktop\\xyz.txt";
    private FileOutputStream file;
    private String leftOver = "abcdefg";
    private Node root;
    private BufferedOutputStream bw;
    String ret;

    public Decompress(String fileName) throws IOException {

        this.smsText = fileName;

        this.file = new FileOutputStream(fileName.substring(0, fileName.length() - 5));
        this.fis = new FileInputStream(fileName);
        this.bw = new BufferedOutputStream(file);

        byteCount(fis);
        readBytes(fileName);
        buildTree(sb.toString());
        decodeTree(leftOver);

        bw.close();
    }

    private void readBytes(String fileName) throws IOException {

        int val = 0;
        byte[] arr = new byte[fis.available()];

        while (val != -1) {
            val = fis.read();
            if (val != -1) {

                String s = Integer.toBinaryString(val);

                if (s.length() < 8) {
                    StringBuilder build = new StringBuilder();
                    int num = 8 - s.length();
                    for (int x = 0; x < num; x++) {
                        build.append("0");
                    }
                    build.append(s);
                    sb.append(build);

                }
                else {
                    sb.append(s);
                }
            }
        }
    }


    private int byteCount(FileInputStream fis) throws IOException {

        byte[] b = new byte[4];
        fis.read(b);

        for (int x = 0; x < b.length; x++) {
            this.i = i << 8;
            this.i = i | (int) (b[x] & 0xff);
        }
        return i;
    }

    @SuppressWarnings("unchecked")
    private void buildTree(String str) {
        // System.out.println("length"+str.length());
        Stack<Node> stack = new Stack<Node>();
        str.charAt(0);

        this.root = new Node();
        stack.push(root);

        Node node = null;

        int index = 1;

        while (!stack.empty() && index < str.length()) {

            if (str.charAt(index) == '0') {
                node = new Node();
            }
            else if (str.charAt(index) == '1') {

                String s = str.substring(index + 1, index + 9);

                int charCode = Integer.parseInt(s, 2);
                byte b = (byte) charCode;

                node = new Node(b, 0);
                index += 8;
            }
            if (stack.peek().getLeft() == null) {
                stack.peek().setLeft(node);
            }
            else {
                stack.peek().setRight(node);
                stack.pop();
            }
            if (node.getData() == null) {
                stack.push(node);
            }
            index++;
        }
        this.leftOver = str.substring(index, str.length());
    }
    public String  decodeTree(String str) throws IOException {
        int length = str.length();
        int num = 0;
        for (int x = 0; x < i; x++) {
            Node curr = root;
            while (!curr.isLeaf() && num < length) {
                if (str.contains("becoz")) {
                     ret=str.replace("coz", "cause");
                    curr = curr.getLeft();
                    num++;

                }
                else if (str.contains("jan")) {
                    ret=str.replace("jan", "january");
                    curr = curr.getLeft();
                    num++;

                } else if (str.contains("alt")) {
                    ret=str.replace("alt", "altitude");
                    curr = curr.getLeft();
                    num++;

                }
                else {
                    curr = curr.getRight();
                    num++;

                }
            }

        }
        return ret;
    }
}


