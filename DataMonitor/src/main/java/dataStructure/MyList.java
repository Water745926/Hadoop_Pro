package dataStructure;

/**
 * @description: fanzuan
 * @author: lijiayu
 * @create: 2020-04-13 10:55
 */
public class MyList {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        MyList nlist = list.convers();
        System.out.println(nlist);
    }
    //内部类
    class Node {
        public Node preious;
        public Node next;
        public int e;
    }

    //首节点，为节点，长度
    public Node first;
    public Node last;
    public int size;

    // 增加尾节点
    public void addLast(int e) {
        Node node = new Node();
        node.e = e;
        if (this.size == 0) {
            this.first = node;
            this.last = node;
        } else {
            this.last.next = node;
            node.preious = this.last;
            this.last = node;
        }
        size++;
    }

    //增加首节点
    public void addFirst(int e) {
        Node node = new Node();
        node.e = e;
        if (this.size == 0) {
            this.first = node;
            this.last = node;
        } else {
            this.first.preious = node;
            node.next = this.first;
            this.first = node;
        }
        size++;
    }

    //重写toString方法
    @Override
    public String toString() {
        //开始添加[
        String ret = "[";
        // 遍历
        if (this.size != 0) {
            Node node = this.first;
            for (int i = 0; i < this.size-1; i++) {
                ret += node.e + ",";
                node = node.next;
            }
            ret += node.e ;
        }
        //末尾添加]
        ret += "]";
        return ret;
    }

    //反转
    public MyList convers() {
        MyList list = new MyList();
        int len = this.size;
        for (int i = 0; i < len; i++) {
            list.addFirst(this.first.e);
            this.first=this.first.next;
        }
        return list;
    }
}









/*


    // 内部类Node
    class Node {
        public Node previous;
        public Node next;
        public E e;
    }

    public Node first;
    public Node last;
    public int size;

    //增加尾节点：
    public void addLast(E e) {
        Node node = new Node();
        node.e = e;
        if (this.size == 0) {
            this.first = node;
            this.last = node;
        } else {
            //挂节点
            this.last.next = node;
            node.previous = this.last;
            this.last = node;
        }
        size++;
    }

    //增加首节点：
    public void addFirst(E e) {
        Node node = new Node();
        node.e = e;
        if (this.size == 0) {
            this.first = node;
            this.last = node;
        } else {
            // 挂节点
            this.first.previous = node;
            node.next = this.first;
            this.first = node;
        }
        size++;
    }

    //反转
    public MyList convers() {
        MyList tmplist = new MyList();
        int len = this.size;
        for (int i = 0; i < len; i++) {
            tmplist.addFirst(this.first.e);
            this.first = this.first.next;
//            System.out.println(this);
        }
        return tmplist;
    }

    @Override
    public String toString() {
        String ret = "[";
        if (size != 0) {
            Node target = this.first;

            for (int i = 0; i < size - 1; i++) {
                ret += target.e + ", ";
                target = target.next;
            }
            ret += target.e;
        }
        return ret + "]";
    }
*/