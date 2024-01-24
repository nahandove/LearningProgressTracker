Project assignment from JetBrains Academy (www.hyperskill.org), Java Developer track.

Summary: A student record application that tracks and summerize the learning progress 
of a group of students in four courses.

The application is a simplified version of a software used in a student record office. 
New students can enroll in any or all of the four courses, complete new assignments to
receive credits, and graduate from a course once they fulfilled the required credits for
the course. The application also allows the user to search for specific student records
and display statistical data, such as course difficulty ratings and student rankings.

The user is first prompted to enter a command. A number of commands are available for 
the user to enter:
 
1. add students
2. list
3. add points
4. find
5. statistics
6. notify
7. back
8. exit

If the user entered any other commands, the program prints the following error message:
Error: unknown command!

Below are the commands in detail. For commands 1-5, The user can also enter "back" to 
return to the main menu:

1. add students

The user is prompted to enter the student's name and email address as a string separated
by a blank space (e.g. John Smith john.smith@gmail.com). 

The student must have a first name and a last name consisting of latin
letters. The first word of student name is the first name, and the last name can be several
words long (e.g. Manuel de Silva Pereira, where "Manuel" is the first name and "de Silva
Pereira" is the last name). A hyphen and apostrophe, e.g. Mary-Ann O'Brian, are also 
allowed in a name, but not as the first or the last character in each name-part.

An email address must be in the form of a@b.c, where a is the local-part, b is the second-
level domain, and c is the top-level domain. The local-part can be any alphanumeric
characters separated by a dot, underscore, or dash, while the domain part must contains
a dot that separates the second-level and top-level domain, each of which consists of
alphanumeric characters.

Error messages "Incorrect first name.", "Incorrect last name.", and "Incorrect email." would
be thrown if the user input invalid first name, last name, or email address. In addition,
if the input email already exists in the system, the user would receive the error message
"This email is already taken."

Once the verification process is passed, the student receives an id number and is able
to enroll in courses.

2. list

The application lists all the existing student ids in the records. If no student exists
in the record. The application returns the message, "No students found."

3. add points

The user is prompted to input the student id followed by credits in each of the four courses
(Java, DSA, Databases, Spring), separated by a blank space. 

The credits represent the grades the student receives for each assignment. If the student is
not enrolled in a course, the user must still enter the grade of 0 for that course, and the
"number of assignments" parameter would not increase. Negative grades are not allowed, and
there must be four grades, one per course (even if the student is not enrolled in a course). 

The application validates that the student of the given ID exists and that the grades are
correctly entered. If the ID does not exist, the error message "No student is found for id="
followed by user input is given. If the grades were incorrectly entered, the error message
"Incorrect points format." is given.

Once the application verifies the student grades, the grades are entered into the student's
record and available for statistical analyses.

4. find

The user is promted to enter a student ID to check a student's current progress (total
credits) in each course. The student's progress is printed in the following format: 
{student-ID} points: Java={Java course credits}; DSA={DSA course credits}, Databases=
{Databases course credits}, Spring={Spring course credits}. 

5. statistics

A major part of this application is to provide statistical summaries on courses and students.
When the "statistics" command was entered, the program lists the general statistics according
to the following criteria:

a. Most popular
b. Least popular
c. Highest activity
d. Lowest activity
e. Easiest course
f. Hardest course

a and b (Most and least popular) refers to the number of students enrolled in a particular
course. A student is enrolled if they have completed at least one assignment in the course
and received a grade. If two or more course have the same highest or lowest enrollment, all
the courses are listed. If all courses have the same number of enrolled students, they are
all listed under "Most popular".

c and d (Highest and lowest activity) refers to the number of completed assignments. The
more assignments completed in a course, the more activity a course has. If two or more 
courses have the same activity, they are listed under the same criterion. If all courses
have the same activity, they are all listed under "Highest activity".

e and f (Easiest and hardest course) refers to the average grade of students per assignment.
A student earns a grade after completing each assignment, and the program calculates the 
average grade earned by all student across all assignments given in a course. The course
with the highest average grades per assignment is the easiest course, and vice versa. If
more than one course has the same average grade per assignment, they are listed under the
same criterion. If all courses have the same average grade per assignment, they are all
listed under "Easiest course".

If no student is enrolled in any of the courses yet, "n/a" is shown after all criterion.
"n/a" is also shown after the "Least popular", "Lowest activity", and "Hardest course" if
all courses have the same statistical result.

After listing the general statistics, the program prompts the user to enter a particular
course or "back" to return to the main menu. If the user enters a course name, the program
lists the IDs of all the students enrolled in the course and their total credits in the
course, sorted from the highest to the lowest. The program also calculates the "percent
completion" for the student in question, i.e. the current total credits of the student, 
divided by the necessary credits to graduate (600 for Java, 400 for DSA, 480 for Databases,
and 550 for Spring). For instance, if "Java" is entered with two students, the program
displays:

Java
id     points    completed
10000  21        4.4%
10001  8         1.7%

6. notify

When this command is entered, any student who completed a course (fulfilled all the credit
requirements) receives a "graduation message" in the following example format:

To: john.smith@gmail.com
Re: Your Learning Progress
Hello, John Smith! You have accomplished our Java course!

A student is notified once per each completed course, after which they are considered a 
"graduate" of the course and will no longer receive further notifications. After all 
"graduates" are notified, the program prints out the number of students notified in this
sitting.

7. back

When "back" is entered in the main menu command selection, the program prints out "Enter
'exit' to exit the program.

8. exit

This command closes the program.

January 24th, 2024--description by E. Hsu (nahandove@gmail.com)
