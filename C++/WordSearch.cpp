//
//  WordSearch.cpp
//  CS263-HW4
//

#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>  // needed for transform()
#include <exception>
#include <regex>
#include "WordSearch.h"

WordSearch::WordSearch() {
    /* default constructor requires no additional code */
}

WordSearch::WordSearch(const string& topdir, const string& ignore_file) {
    load_ignored_words(ignore_file);
    /* filter the directory only for files ending with "txt" */
    gvsu::FileSystem dir (topdir, regex{"txt$"});

    for (auto entry : dir) {
        cout << "Reading words from " << entry.second << endl;
        read_words (entry.first + "/" + entry.second);
    }
}

void WordSearch::load_ignored_words(const string& fname) {
    ifstream ign_file (fname);
    if (!ign_file.is_open()) {
        throw ios_base::failure {"Unable to load ignored.txt"};

    }
    string word;
    while (getline(ign_file, word))
        ignored_words.insert(word);
    ign_file.close();
}

void WordSearch::read_words(const string &file_name)
{
    /* a word is THREE OR MORE alphabetical characters (lowercase) */
    const regex word_re {"[a-z]{3,}"};

    /* Alternate declaration of the above regular expr
     const regex word_re {"[[:alpha:]]{3,}"};
     */
    ifstream txt (file_name); /* file is aumatically open */

    string one_line;

    int line = 1;
    string prev;
    while (getline(txt, one_line)) {
        /* change to lowercase */
        transform(one_line.begin(), one_line.end(), one_line.begin(), ::tolower);
        /* iterate over the string using a regular expression */
        auto re_begin = sregex_iterator {one_line.begin(),one_line.end(), word_re};
        auto re_end = sregex_iterator{};
        for (auto word = re_begin; word != re_end; ++word) {
            /* if the word is in the ignored list, don't print it */
            if (ignored_words.find(word->str()) == ignored_words.end())
            {
                /* TODO: REMOVE the following cout line */
                //cout << "Current word is " << word->str() << endl;

                /* TODO: use the current word to update your data structures */
                wordCount++;
                wordMap[word->str().length()].insert(word->str());
                frequency[word->str()]++; //count word frequency

                if (prev.length() > 0) {
                    nextWord[prev][word->str()]++;
                }
                prev = word->str();

            }
        }
        line++;
    }
    txt.close(); /* close the file */

}


unsigned long WordSearch::word_count() const {
    /* TODO complete this function */
    return wordCount;
}

/**
 * This method shall return all words of a certain length found in the files.
 * When no words of the given length, the method should return an empty set
 * (and not a nullptr!)
 * @param L
 * @return
 */
set<string> WordSearch::words_of_length (int L) const {
    /* TODO complete this function */
    if (wordMap.find(L) != wordMap.end())
        return wordMap.at(L);
    return set<string>();   /* return an empty set */
}

/**
 * Returns a set of words that occur the most.
 * The function actually returns a pair of an unsigned int and a string set.
 * We use a string set in case there are several words occurring the most.
 * @return
 */
pair<unsigned int,set<string>> WordSearch::most_frequent_words() const
#ifndef _WIN32
throw (length_error)
#endif
{
    set<string> words;
    /* TODO complete this function */
    unsigned int nums = 0;
    int max = 0;

    if (frequency.empty())
        throw length_error("empty");

    for(auto it: frequency){
        if(it.second > max){
            nums = 0;
            words.clear();
            max = it.second;
        }
        if(it.second == max){
            nums += it.second;
            words.insert(it.first);
        }
    }
    return make_pair(nums, words);
}

/**
 * Is simply the opposite of the previous function,
 * except this function returns only the string set (not a pair)
 * @param count
 * @return
 */
set<string> WordSearch::least_frequent_words(int count) const {
    set<string> words;

    /* TODO complete this function */
    for(auto it: frequency){
        if(it.second <= count)
            words.insert(it.first);
    }

    return words;
}

/**
 * Based on the statistics collected from all the input files,
 * you can determine which word has the highest probability to following
 * another word.
 * @param word
 * @return
 */
string WordSearch::most_probable_word_after(const string& word) const {

    /* TODO complete this function */
    string mostProbable;
    int max = 0;

    if (nextWord.find(word) != nextWord.end()) {
        auto mapW = nextWord.at(word);

        for (auto it: mapW) {
            if (it.second > max) {
                mostProbable = it.first;
                max = it.second;
            }
        }
    }

    return mostProbable;
}
