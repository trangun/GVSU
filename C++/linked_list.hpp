//
// Created by Hans Dulimarta
//

#ifndef LINKEDLISTWITHTEMPLATE_LINKED_LIST_H
#define LINKEDLISTWITHTEMPLATE_LINKED_LIST_H
#include <memory>
#include <stdexcept>
#include <set>
#include <iostream>

using namespace std;

namespace gv {
    template <typename T> class linked_list {
    private:
        struct Node;
    public:
        /**
         * Default constructor
         */
        linked_list() {
            head.reset();
            tail.reset();

        }

        /**
         * Check if the list contains data items
         * @return true if it is empty, false otherwise
         */
        bool is_empty() const {
            return this->head == nullptr;
        }

        /**
         * Count the number of items in the list
         * @return the number of items
         */
        int size() const {
            int count = 0;
            shared_ptr<Node> p = head;
            while (p != nullptr){
                count++;
                p = p->next;
            }
            return count;
        }

        /**
         * Make the list empty
         */
        void clear() {
            shared_ptr<Node> temp = head;
            while (temp != nullptr){
                temp = head->next;
                head = nullptr;
                head=temp;
            }
        }

        /**
         * Return the first data in the list, or throw an length_error exception when the list is empty
         * @return the data stored in the first node
         * @throw length_error when the list is empty
         */
        const T& front() const {
            if (is_empty()) {
                throw length_error("empty");
            } else {
                return head->data;
            }
        };

        /**
         * Return the last data in the list, or throw an length_error exception when the list is empty
         * @return the data stored in the first node
         * @throw length_error when the list is empty
         */
        const T& back() const {
            if (is_empty()) {
                throw length_error ("empty");
            } else {
                auto tailLocked = this->tail.lock();
                return tailLocked->data;
            }
        };

        /**
         * Store x as the first data item in the list
         * @param x the value to store
         */
        void push_front(const T& x) {
            shared_ptr<Node> temp = make_shared<Node>();
            temp->data = x;
            temp->next = nullptr;

            if (is_empty()) {
                this->head = temp;
                this->tail = temp;
            } else {
                temp->data = x;
                head->prev = temp;
                temp->next = head;

                head = temp;
            }
        }

        /**
         * Store x as the last data item in the list
         * @param x the value to store
         */
        void push_back(const T& x) {
            shared_ptr<Node> temp = make_shared<Node>();
            temp->data = x;
            temp->next = nullptr;


            if (!is_empty()) {
                temp->prev = tail;
                auto tailLocked = this->tail.lock();
                tailLocked->next = temp;

            } else {
                this->head = temp;
                //this->tail = temp;
            }
            this->tail = temp;
        }

        /**
         * Insert x as a new data item at the specified position in the list.
         * As an example: if the list holds string data {"Carbon", "Oxygen"}
         * insert_into ("Neon", 0) => {"Neon", "Carbon", "Oxygen"}
         * insert_into ("Neon", 1) => {"Carbon", "Neon", "Oxygen"}
         * insert_into ("Neon", 2) => {"Carbon", "Oxygen", "Neon"}
         * insert_into ("Neon", N) => throw out_of_range when N < 0 or N > 2
         *
         * @param x the value to store
         * @param pos the position at which x is to be inserted (0 = first)
         * @throw out_of_range exception when pos is invalid (negative or larger than list size)
         */
        void insert_into (const T&x, int pos) {
            shared_ptr<Node> temp = make_shared<Node>();
            auto current = head;
            temp->data = x;
            temp->next = nullptr;
            if ((pos < 0) || (pos > size())) {
                throw out_of_range("out of range");
            } else if (is_empty()) {
                head = temp;
                tail = temp;
            } else if (pos == 0) {
                push_front(x);
            } else if (pos==size()) {
                push_back(x);
            } else {
                for (int i = 0; i < pos -1; i++) {
                    current = current->next;
                }
                temp->next = current->next;
                current->next = temp;
            }
        }


        /**
         * Remove the data at a given position
         * As an example: if the list holds string data {"Carbon", "Oxygen", "Fluor"}
         * remove_from(0) => {"Oxygen", "Fluor"}
         * remove_from(1) => {"Carbon", "Fluor"}
         * remove_from(2) => {"Carbon", "Oxygen"}
         * remove_from(3) => throw out_of_range exception
         * remove_from(-1) => throw out_of_range exception
         *
         * @param pos the position at which the data is to be removed (0 = first)
         * @throw out_of_range exception when pos is invalid (negative or >= list size)
         */
        void remove_from (int pos) {
            shared_ptr<Node> current = head;
            int count = 0;
            if (pos < 0 || pos >= size()) {
                throw out_of_range ("out of range");
            } else if (is_empty()) {
                return;
            } else if (pos == 0) {
                pop_front();
            }else {
                for (int i = 0; i < pos - 1; i++) {
                    current = current->next;
                }
                current->next = current->next->next;
            }
        }

        /**
         * Return the data stored at a given position
         * @param pos the position at which the data is to be retrieved (0 = first)
         * @return the data at the requested position
         * @throw out_of_range exception when pos is invalid (negative or >= list size)
         */
        const T& at (int pos) const {
            auto temp = head;
            if (pos < 0 || pos >= size()) {
                throw out_of_range("out of range");
            } else {
                for (int i = 0; i<pos; i++) {
                    temp = temp->next;
                }
            }
            return temp->data;
        };

        /**
         * Remove the first data item
         * @throw length_error when the list is empty
         */
        void pop_front() {
            if (is_empty()) {
                throw length_error("empty");
            } else {
                head = head->next;
            }
        }

        /**
         * Remove the last data item
         * @throw length_error when the list is empty
         */
        void pop_back() {
            auto tailLocked = tail.lock();
            if (is_empty()) {
                throw length_error("empty");
            } else if (head == tailLocked) {
                head = head->next;
            } else {
                remove_from(size()-1);
            }
        }

        /**
         * Implement self-adjusting list (Problem 3.30b). Search for a given item
         * and move the item to the front of the list (if found)
         * @param val data item to search for in the list
         * @return true if the item is found, false otherwise
         */
        bool find(const T& val) {
            int pos = 0;
            shared_ptr<Node> temp = head;
            if (is_empty())
                return false;

            while (pos < size()) {
                if (temp->data == val) {
                    if (pos > 0) {
                        remove_from(pos);
                        push_front(val);
                    }
                    return true;
                } else {
                    temp = temp->next;
                    pos++;
                }
            }
            return false;

        }

    private:
        // You may add private data/function here

        // But, DO NOT CHANGE ANYTHING BELOW THIS LINE
        shared_ptr<Node> head;
        weak_ptr<Node> tail;
        struct Node {
            T data;
            shared_ptr<Node> next;
            weak_ptr<Node> prev;
        };
    };
};
#endif //LINKEDLISTWITHTEMPLATE_LINKED_LIST_H
