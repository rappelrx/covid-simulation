/**
 * Copyright (c) 2023 @rappelrx on GitHub
 * 
 * This program tests whether an input string has a pattern that matches
 * the regular expression: 'x' OR 'y', followed by concatenation of ANY 
 * number of repetitions of 'z'.
 * 
 * Examples of matched strings: 'x', 'y', 'xzzzz', 'yzzzzzz'
 * Examples of non-matched strings: 'xy', 'xyzzzz', 'zzzz', empty string
 */

#include <iostream>
#include <string>
#include <regex>
using namespace std;

// Define the alphabet as {'x', 'y', 'z'}
struct Alphabet {
    const char LOWERCASE_X = 'x';
    const char LOWERCASE_Y = 'y';
    const char LOWERCASE_Z = 'z';
};

/**
 * Helper method to determine if input is over the defined alphabet.
 * 
 * @param expression The string input in question
 * @return true if all letters are in alphabet, false otherwise
 */
bool inAlphabet(const string & expression) {
    struct Alphabet sigma;
    string::const_iterator letter;
    for (letter = expression.begin(); letter != expression.end(); letter++) {
        if (*letter != sigma.LOWERCASE_X && *letter != sigma.LOWERCASE_Y
            && *letter != sigma.LOWERCASE_Z) {
            return false;
        }
    }
    return true;
}

/**
 * Helper method to count number of 'z' occurences in expression.
 * 
 * @param expression The string input in question
 * @return int representation of number of 'z' occurences in expression
 */
int zCount(const string & expression) {
    struct Alphabet sigma;
    int count = 0;
    string::const_iterator letter;
    for (letter = expression.begin()+1; letter != expression.end(); letter++) {
        if (*letter == sigma.LOWERCASE_Z) {
            count++;
        }
    }
    return count;
}

int main() {
    struct Alphabet sigma; 
    string expression;
    cout << "Please enter a string over the alphabet: ";
    cin >> expression;

    // Define regular expression pattern
    regex reg("^(x|y)(z*)$");

    // Determine if input is over the alphabet {'x', 'y', 'z'}
    if (!inAlphabet(expression)) {
        cerr << "One or more letters is not in the alphabet." << endl;
        return 1;
    }

    // Determine if input matches the regular expression
    if (regex_search(expression, reg))
        cout << expression << " matches the regular expression." << endl
        << "There are " << zCount(expression) << " occurences of " <<
        sigma.LOWERCASE_Z << " in this expression." << endl;
    else 
        cout << expression << " does not match the regular expression." << endl;

    return 0;
}