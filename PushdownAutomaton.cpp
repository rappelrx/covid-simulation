/******************************************************************************

Author: Rappel Ricafort, May 2022
C++ Compiler: onlinegdb.com/online_c++_compiler

This program functions like a Pushdown Automaton (PDA) that recognizes
the nonregular but context-free language {0^n 1^m 0^n : n, m >= 0}.

*******************************************************************************/

#include <iostream>
#include <string>
#include <list>

using namespace std;

int compute(string input) {
    list<char> stack;
    bool visitedMidState = false; // Set to true when first '1' (if any) from input is read.
    for (char c : input) {
        if (c != '0' && c != '1')
            return 1; // This means symbol is not in the input alphabet: {0,1}

        if (visitedMidState == false) {
            if (c == '0')
                stack.push_front('0');
            else if (c == '1')
                visitedMidState = true;
        }
        else if (visitedMidState = true) {
            if (c == '0') {
                if (stack.front() == '0')
                    stack.pop_front(); // Pop 0 for each 0 that was read before the 1's in input.
                else
                    return 1; // This means there are more 0's in the back than in the front in input.
            }
        }
    }
    if (stack.size() == 0)
        return 0; // PDA accepts this input of the form 0^n 1^m 0^n
    else {
        if (visitedMidState == false) { // if no 1's were read from input
            if (input.length() % 2 == 0)
                return 0; // PDA accepts this input of EVEN length containing only 0's.
            else
                return 1; // PDA rejects this input of ODD length containing only 0's.
        }
        else
            return 1; // This means there are more 0's in the front than in the back in input.
    }
}

int main() {
    int acceptance = compute(""); // INSERT INPUT HERE
    if (acceptance == 0) {
        cout << "The PDA accepts this input." << endl;
        return 0;
    }
    else {
        cout << "This input is rejected." << endl;
        return 1;
    }
}
