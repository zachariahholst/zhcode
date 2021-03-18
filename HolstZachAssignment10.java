/*
 * Zach Holst
 * CS 1150
 * Novemeber 14th, 2019
 * Assignment 10
 * The purpose of this assignment is to get an introduction to
 * objects and classes.  A book class and a library class are 
 * created, and different manipulations are done on these classes
 * to create, store, evaluate, and display the objects
 */
public class HolstZachAssignment10 {

	public static void main(String[] args) {

		//define constant for book array length
		final int BOOK_ARRAY_LENGTH = 5;

		//create a int of some number for the library class to determine
		//how many books there are in the library
		int maxNumBooks = 100;

		//create book objects, defining what the title, author, and page number
		//of each book objecy is
		Book book1 = new Book("Data Structures & Algorithms", "Michael Goodrich", 720);
		Book book2 = new Book("The C++ Programming Language", "Bjarne Stroustrup", 1376);
		Book book3 = new Book("Don't Make Me Think", "Steve Krug", 216);
		Book book4 = new Book("The Elements of Java Style", "Jim Shur", 144);
		Book book5 = new Book("The Design of Everyday Things", "Don Norman", 368);

		//create a book array (different from the books array within the library class)
		Book[] bookArray = new Book[BOOK_ARRAY_LENGTH];

		//Add all book objects to the book array
		bookArray[0] = book1;
		bookArray[1] = book2;
		bookArray[2] = book3;
		bookArray[3] = book4;
		bookArray[4] = book5;

		//print out all of the books, displaying their title, author, and page number
		System.out.println("***************************************************************");
		System.out.println("                         Books in Library");
		System.out.println("***************************************************************");
		System.out.println("");
		System.out.printf("%1s%35s%22s", "Title", "Author", "Pages");
		System.out.println("");

		for (int count = 0; count < bookArray.length; count++) {

			System.out.printf("%-34s%-23s%1d", bookArray[count].getTitle(), bookArray[count].getAuthor(), bookArray[count].getPages());
			System.out.println("");
		}

		//find which book has the least number of pages with the use of a for loop
		//and an if statement, then display the book with the least number of page's
		//title and author
		System.out.println("");
		System.out.println("Book with least number pages");
		System.out.println("--------------------------------------------------");


		int leastNumPages = bookArray[1].getPages();;
		String titleLeastNumPages = "";
		String leastNumPagesAuthor = "";

		for(int count = 0; count < bookArray.length; count++) {

			if(bookArray[count].getPages() <= leastNumPages) {

				leastNumPages = bookArray[count].getPages();
				titleLeastNumPages = bookArray[count].getTitle();
				leastNumPagesAuthor = bookArray[count].getAuthor();

			}

		}

		System.out.println("Title:   " + titleLeastNumPages );
		System.out.println("Author:  " + leastNumPagesAuthor);

		//find which book has the longest title with a for loop and if statement,
		//reading the length of each title as a string
		//display the book with the longest title's title and author
		System.out.println("");
		System.out.println("Book with the longest title");
		System.out.println("--------------------------------------------------");

		int longestTitleLength = bookArray[1].getTitle().length();
		String longestTitle = "";
		String longestTitleAuthor = "";

		for(int count = 0; count < bookArray.length; count++) {

			if(bookArray[count].getTitle().length() >= longestTitleLength) {

				longestTitleLength = bookArray[count].getTitle().length();
				longestTitle = bookArray[count].getTitle();
				longestTitleAuthor = bookArray[count].getAuthor();

			}
		}

		System.out.println("Title:   " + longestTitle );
		System.out.println("Author:  " + longestTitleAuthor);
		System.out.println("");
		System.out.println("");


		//Create a single library object where the book objects
		//will be stored too
		Library library = new Library(maxNumBooks);

		//use the method within the library class to add all the book
		//objects to the books array within the library class
		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);
		library.addBook(book4);
		library.addBook(book5);

		//display again all the books, displaying their title, author, and page number.
		//This time using the methods within the library class to access each book at 
		//their given index to recieve the information from the Book object to print
		System.out.println("***************************************************************");
		System.out.println("                         Books in Library");
		System.out.println("***************************************************************");
		System.out.println("");
		System.out.printf("%1s%35s%22s", "Title", "Author", "Pages");
		System.out.println("");


		for (int count = 0; count < bookArray.length; count++) {

			System.out.printf("%-34s%-23s%1d",library.getBookAtIndex(count).getTitle(), library.getBookAtIndex(count).getAuthor(), library.getBookAtIndex(count).getPages());
			System.out.println("");
		}

		//find which book has the least number of pages with the use of a for loop
		//and an if statement, then display the book with the least number of page's
		//title and author.  Use methods within the library class this time
		System.out.println("");
		System.out.println("Book with least number pages");
		System.out.println("--------------------------------------------------");

		leastNumPages = library.getBookAtIndex(1).getPages();
		titleLeastNumPages = "";
		leastNumPagesAuthor = "";

		for(int count = 0; count < bookArray.length; count++) {

			if(library.getBookAtIndex(count).getPages() <= leastNumPages) {

				leastNumPages = library.getBookAtIndex(count).getPages();
				titleLeastNumPages = library.getBookAtIndex(count).getTitle();
				leastNumPagesAuthor = library.getBookAtIndex(count).getAuthor();

			}

		}


		System.out.println("Title:   " + titleLeastNumPages );
		System.out.println("Author:  " + leastNumPagesAuthor);

		//find which book has the longest title with a for loop and if statement,
		//reading the length of each title as a string
		//display the book with the longest title's title and author.
		//use methods within the library class this time
		System.out.println("");
		System.out.println("Book with the longest title");
		System.out.println("--------------------------------------------------");

		longestTitleLength = library.getBookAtIndex(1).getTitle().length();
		longestTitle = "";
		longestTitleAuthor = "";

		for(int count = 0; count < bookArray.length; count++) {

			if(library.getBookAtIndex(count).getTitle().length() >= longestTitleLength) {

				longestTitleLength = library.getBookAtIndex(count).getTitle().length();
				longestTitle = library.getBookAtIndex(count).getTitle();
				longestTitleAuthor = library.getBookAtIndex(count).getAuthor();

			}
		}

		System.out.println("Title:   " + longestTitle );
		System.out.println("Author:  " + longestTitleAuthor);

	}

}

//Creates a book class.  Allows Book objects to be made to hold 
//a title as a string, an author as a string, and number of 
//pages as an int
class Book {

	private String title;
	private String author;
	private int pages;

	public Book (String title, String author, int pages) {
		this.title = title;
		this.author = author;
		this.pages = pages;

	}

	//These three methods are getters, which create the book objects
	public String getTitle() {

		return title;

	}

	public String getAuthor() {

		return author;

	}

	public int getPages() {

		return pages;

	}

}

//Creates a library class that holds a string of book objects.
class Library {

	private Book[] books;
	private int numBooks;

	public Library(int maxNumBooks) {

		this.books = new Book[maxNumBooks];

		this.numBooks = 0;

	}

	//returns how many books are left in the library
	public int getNumBooks() {

		return numBooks;

	}

	//this method accesses a book at the given index of the
	//book array
	public Book getBookAtIndex(int index) {

		return books[index];

	}

	//This method allows book objects to be added to the book array
	//within the library class
	public void addBook (Book bookToAdd) {

		books[numBooks] = bookToAdd;

		numBooks++;

	}

}