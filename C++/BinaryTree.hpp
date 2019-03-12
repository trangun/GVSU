//
// Created by Hans Dulimarta.
//

#ifndef BINARYTREES_BINARYSEARCHTREE_H
#define BINARYTREES_BINARYSEARCHTREE_H
#include <memory>
#include <iostream>
#include <stdexcept>
#include <queue>
#include <stack>

using namespace std;

namespace gv {
    template<typename E>     // textbook code E is Comparable
    class BinaryTree {

    public:
        /** WARNING: You will find a few overloaded functions declared in
         * both the public and private sections. This design is intentional
         * because many tree algorithms are recursive and the private counterpart
         * are the ones implementing the recursive work and they usually
         * require additional input argument(s).
         *
         * The non-recursive public functions simply call their recursive
         * counterpart to initiate the recursive work.
         */

        /**
         * Make the tree into an empty tree
         */
        void makeEmpty() noexcept {
            // TODO: complete this function
            makeEmpty(root);
        }

        /**
         * Insert a new data into the BST while maintaining no duplicate entries
         * @param item
         * @return true if the item can be inserted (no duplicate), false if the tree
         * already contains the same data item.
         */
        bool insert(const E &item) noexcept {
            // TODO: complete this function by invoking a private recursive function
            return insert(item, root);
        }

        /**
         * Remove an item from the tree
         * @param item data to remove
         * @return true is the data is removed, false if the data is not in the tree
         * @throw length_error on an attempt to remove from an empty tree
         */
        bool remove(const E& item) {
            // TODO: complete this function by invoking a private recursive function
            if (isEmpty())
                throw length_error("Empty");
            return remove(item, root);
        }

        /**
         * Print the tree using in-order traversal. Separate data item by a single space
         * @param out destination of the print out
         */
        void printTree(ostream &targetStream = cout) const noexcept {
            // TODO: complete this function by invoking a private recursive function
            // Be sure to use "targetStream" (and NOT cout) to print your data
            // For instance the following snippet would print "Hello"
            //   targetStream << "Hello";
            if (isEmpty())
                targetStream << "Hello" << endl;
            else
                printTree(root, targetStream);
        }

        /**
         * Find the smallest value in the tree
         * @return the smallest value
         * @throw length_error if the tree is empty
         */
        const E findMin() const {
            // TODO: complete this function
            if(isEmpty())
                throw length_error("Tree is empty");
            else
                return findMin(root);
        };

        /**
         * Find the largest value in the tree
         * @return the largest value
         * @throw length_error if the tree is empty
         */
        const E findMax() const {
            // TODO: complete this function
            if(isEmpty())
                throw length_error("Tree is empty");
            else
                return findMax(root);

        }

        /**
         * Check if the given item is stored in the tree
         * @param val
         * @return true if the item is stored in the tree, false otherwise
         */
        bool contains(const E &val) const noexcept {
            // TODO: complete this function by invoking a private recursive function
            return contains(val, root);
        }

        /**
         * Is the tree empty?
         * @return
         */
        bool isEmpty() const noexcept {
            // TODO: complete this function
            return root == nullptr;
        }

        /**
         * Return the number of nodes in the tree (Problem 4.31a)
         * @return
         */
        int numberOfNodes() const noexcept {
            // TODO: complete this function by invoking a private recursive function

            return numberOfNodes(root);

        }

        /**
         * Return the number of leaves in the tree (Problem 4.31b)
         * @return
         */
        int numberOfLeaves() const noexcept {
            // TODO: complete this function by invoking a private recursive function
            return numberOfLeaves(root);
        }

        /**
         * Return the number of full nodes (Problem 4.31c). A full node is a node with exactly two children
         * @return
         */
        int numberOfFullNodes() const noexcept {
            // TODO: complete this function by invoking a private recursive function
            return numberOfFullNodes(root);
        }

        /**
         * Remove all the leaves from the tree and keep the data in these leaves into a vector
         * @return a vector of removed items
         */
        vector<E> remove_leaves() noexcept {
            vector<E> prunedLeaves;
            // TODO: complete this function by invoking a private recursive function
            // include the vector (prunedLeaves) about in your function invocation

            remove_leaves(root);
            return prunedLeaves;
        }

        /**
         * Visit the node in level order (Problem 4.40)
         * @return
         */
        vector<E> levelOrder() const {
            vector<E> out;
            // TODO: complete this function
            queue<Node*> q; //vector to a pointer of node
            // vector<Node> *subTree : a pointer to a vector of node
            Node *temp = root.get();
            if (isEmpty()) return out;
            else q.push(temp);
            //out.push_back(temp->data);

            while (!q.empty()) {
                Node *p = q.front();
                out.push_back(p->data);
                q.pop();

                if (p->left != nullptr) {
                    q.push(p->left.get());
                }
                if (p->right != nullptr) {
                    q.push(p->right.get());
                }
            }
            return out;
        }

        static bool hasLeak() {
            return nodeCount != 0;
        }

        static int allocatedNodes() {
            return nodeCount;
        }

    private:
        struct Node;

        // TODO: write your private functions here
        void makeEmpty(unique_ptr<Node>& sub) {
            if (sub != nullptr) {
                makeEmpty(sub->left);
                makeEmpty(sub->right);
                sub.reset();
            }
        };

        bool insert (const E &x, unique_ptr<Node>& t) const{
            if (t == nullptr) {
                t= make_unique<Node>();
                t->data = x;
                t->left= nullptr;
                t->right= nullptr;
                return true;
            } else if (x < t->data) {
                return insert(x, t->left);
            } else if (x > t->data) {
                return insert(x, t->right);
            } else {
                return false;
            }
        };

        bool remove (const E &x, unique_ptr<Node>& t) const {
            if (t == nullptr) {
                return false;
            }
            if (x < t->data) {
                return remove(x, t->left);
            } else if (x > t->data) {
                return remove(x, t->right);
            } else if (t->left != nullptr && t->right != nullptr) {
                t->data = findMin(t->right);
                remove(t->data, t->right);
                return true;
            } else {
                if (t->left != nullptr) {
                    //Node *temp = t->left.release();
                    // t.reset(temp);
                    t.reset(t->left.release());
                    return true;
                } else {
                    t.reset(t->right.release());
                    return true;
                }
            }
        };

        const E findMin(const unique_ptr<Node>& t) const{
            if (t->left == nullptr)
                return t->data;
            return findMin(t->left);
        };

        const E findMax(const unique_ptr<Node>& t) const {
            Node *temp = t.get();
            if (temp != nullptr)
                while (temp->right.get() != nullptr)
                    temp = temp->right.get();
            return temp->data;
        };

        void printTree (const unique_ptr<Node>& t, ostream & out) const {
            if ( t != nullptr) {
                printTree(t->left, out);
                out << t->data << endl;
                printTree(t->right, out);
            }
        };

        bool contains (const E & x, const unique_ptr<Node>& t) const {
           // unique_ptr<Node> t = move(s);
            if (t == nullptr)
                return false;
            else if (x < t->data) {
                return contains(x, t->left);
            } else if (x > t->data)
                return contains(x, t->right);
            else
                return true;
        };

        int numberOfNodes(const unique_ptr<Node>& t) const {
            if (t == nullptr)
                return 0;
            return 1 + numberOfNodes(t->left) + numberOfNodes(t->right);
        };

        int numberOfLeaves(const unique_ptr<Node>& t) const {
            if (t == nullptr)
                return 0;
            else if (t->left == nullptr && t->right == nullptr)
                return 1;
            return numberOfLeaves(t->left) + numberOfLeaves(t->right);
        };

        int numberOfFullNodes(const unique_ptr<Node>& t) const {
            if( t == nullptr )
                return 0;
            int isFull = (t->left != nullptr && t->right != nullptr ) ? 1 : 0;
            return isFull + numberOfFullNodes(t->left) + numberOfFullNodes(t->right);
        };

        vector<E> remove_leaves(unique_ptr<Node>& t) {
            vector<E> pruneLeaves;
            if (t == nullptr) {
                return pruneLeaves;
            } else if (t->left == nullptr && t->right == nullptr) {
                pruneLeaves.push_back(t->data);
                t.reset();
                return pruneLeaves;
            }

            remove_leaves(t->left);
            remove_leaves(t->right);
            return pruneLeaves;

        };

        static int nodeCount;
        // TODO: Make necessary modification to the Node
        // structure if you decide to implement a threaded tree
        struct Node {
            Node() {
                nodeCount++;
            }

            ~Node() {
                nodeCount--;
            }
            E data;
            unique_ptr<Node> left, right;

           // Node(const E &theData, unique_ptr<Node> lt, unique_ptr<Node> rt) : data{theData}, left{lt}, right {rt} {}
        };
        unique_ptr<Node> root;

    };

    template<typename E>
    int BinaryTree<E>::nodeCount = 0;
}
#endif //BINARYTREES_BINARYSEARCHTREE_H
