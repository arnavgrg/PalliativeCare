# PalliativeCare
Winner of the Health Grand Prize at TreeHacks 2018, Our application 'PalliativeCare' 
connects volunteers to patients who need palliative care by matching them according 
to their personalities and interests. This allows volunteers to be more compatible with 
their patients and improve the level of comfort they are able to provide. 

Our front-end is a website that was built using HTML/CSS/Javascript and Bootstrap. 
The information that users submit is passed down to Amazon's DynamoDB, where the 
information is stored and then pulled out by our Java program. Our Java program is 
implementing IBM Watson's API to classify users into 3 of 16 personalities. Our 
Java program looks at the users personalities and all of the topics/interests that 
the users entered and compares them with the other type of user(volunteer to patient 
and vice versa), and matches them using machine learning algorithms.

Devpost: https://devpost.com/software/palliativecare 

Team:
- Jason Zhao
- Steven Tat
- Arnav Garg
