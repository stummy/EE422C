Hi this is the README for the last project aka Project 7 aka Cheaters!

This program works by reading line by line a text file and appending each line to
create a really really long string. Then it breaks up the string into chunks of sizes
determined by the user input. Then those chunks of strings are put into a set (encompassing
the whole document) which are later added into an arrayList of sets (the directory).
Each set is compared to one another by using the retainAll method which removes
any elements that two sets have in common. The result is then mapped to the two documents
in a map that is returned and sorted.

We added GUI for easier visualization although because the documents represented as a
square are spawned randomly, shapes may overwrite one another which may cause confusion.
The thicker the line, the more intersections between the two documents :D
