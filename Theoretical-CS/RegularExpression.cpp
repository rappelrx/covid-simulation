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

int main() {
    string input;
    cin >> input;

    // Define regular expression pattern
    regex reg("^(x|y)(z*)$");

    // Determine if input matches the regular expression.
    if (regex_search(input, reg))
        cout << input << " matches the regular expression." << endl;
    else 
        cout << input << " does not match the regular expression." << endl;

    return 0;
}