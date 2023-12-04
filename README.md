# Threaded Search Engine

## Overview
This project implements a text search engine using multiple threads. The program takes a text file and a list of words to search for as input. To parallelize the search process, the program utilizes threads, each responsible for searching an equal portion of the text. To handle potential race conditions, both Mutex Locks and Semaphores are employed.

## Features
- Multithreaded text search for better performance.
- Prevention of race conditions using Mutex Locks and Semaphores.
- Output file containing:
  - Line number of each word in the input file.
  - Thread number that found each word.
  - Timestamp of when the word was found.
  - Timestamp of when the information was written to the output.

## Usage
Compile the program by running `Main.java`.
Sample input files are available in `testcases/` folder.
