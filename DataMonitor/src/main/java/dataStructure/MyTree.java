package dataStructure;

/**
 * @description:
 * @author: lijiayu
 * @create: 2020-04-13 14:53
 * 后续遍历
 */
public class MyTree {
    public static void main(String[] args) {
        MyTree myTree = new MyTree();
        myTree.add(6);
        myTree.add(4);
        myTree.add(8);
        myTree.add(2);
        myTree.add(5);
        myTree.add(7);
        myTree.add(9);
        myTree.add(1);
        myTree.add(3);
        myTree.print();

    }

    // 内部类
    class Node {
        public Node left;
        public Node right;
        public int e;

        public void addNode(int e) {
            //比当前元素大
            if (e > this.e) {
                if (this.right == null) {
                    Node node = new Node();
                    node.e = e;
                    this.right = node;
                } else {
                    this.right.addNode(e);
                }
            } else/*比当前元素小或者相等*/ {
                if (this.left == null) {
                    Node node = new Node();
                    node.e = e;
                    this.left = node;
                } else {
                    this.left.addNode(e);
                }
            }
        }


        // 打印
        public void print() {
            System.out.println(this.e + ",");//先序遍历
            if (this.left != null) {
                this.left.print();
            }
//            System.out.println(this.e + ",");//中序遍历
            if (this.right != null) {
                this.right.print();
            }
//            System.out.println(this.e + ",");//后序遍历
        }
    }


    // 根节点
    private Node root;

    // 添加元素
    public void add(int e) {
        if (this.root == null) {
            Node node = new Node();
            node.e = e;
            this.root = node;
        } else {
            this.root.addNode(e);
        }
    }

    // 打印
    public void print() {
        if (this.root != null) {
            this.root.print();
        }
    }

}

/*

//内部类
class Node {
    Node left;
    Node right;
    int e;

    public void addNode(int e) {
        //比当前元素大：
        if (e > this.e) {
            if (this.right == null) {
                Node node = new Node();
                node.e = e;
                this.right = node;
            } else {
                this.right.addNode(e);
            }
        } else
            */
/*比当前元素小*//*
 {
            if (this.left == null) {
                Node node = new Node();
                node.e = e;
                this.left = node;
            } else {
                this.left.addNode(e);
            }
        }
    }

    public void print() {
        if (this.left != null) {
            this.left.print();
        }
        System.out.println(this.e + ",");
        if (this.right != null) {
            this.right.print();
        }
    }
}

    //根节点
    private Node root;

    //加元素
    public void add(int e) {
        if (this.root == null) {
            Node node = new Node();
            node.e = e;
            this.root = node;
        } else {
            this.root.addNode(e);
        }
    }

    // 打印
    public void print() {
        if (this.root != null) {
            this.root.print();
        }
    }
*/
