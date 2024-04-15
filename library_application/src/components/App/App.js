import './App.css';
import React, {Component} from "react";
import LibraryService from "../../repository/library_repository";
import CountryAdd from "../Countries/countryAdd";
import Header from "../Header/header";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Countries from "../Countries/countries";
import Home from "./home";
import Authors from "../Author/authors";
import AuthorAdd from "../Author/authorAdd";
import BookAdd from "../Books/bookAdd";
import Books from "../Books/books";
import BookEdit from "../Books/bookEdit";
import CategoryAdd from "../Category/categoryAdd";
import Categories from "../Category/categories";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      countries: [],
      authors: [],
      books: [],
      categories: [],
      selectedBook: {}
    }
  }

  render() {
      return (
          <Router>
              <Header />
              <main>
                  <div className="container">
                      <Routes>
                          <Route path="/category/add" element={<CategoryAdd addCategory={this.addCategory}/>}/>
                          <Route path="/country/add" element={<CountryAdd addCountry={this.addCountry} />} />
                          <Route path="/author/add" element={<AuthorAdd addAuthor={this.addAuthor}
                                                                        countries={this.state.countries}/>} />
                          <Route path="/categories" element={<Categories categories={this.state.categories}/>}/>
                          <Route path="/countries" element={<Countries countries={this.state.countries} />} />
                          <Route path="/authors" element={<Authors authors={this.state.authors} />} />
                          <Route path="/book/add" element={<BookAdd addBook={this.addBook}
                          authors={this.state.authors}
                          categories={this.state.categories}/>} />
                          <Route path="/books/edit/:id" element={<BookEdit editBook={this.editBook}
                                                                          authors={this.state.authors}
                                                                          categories={this.state.categories}
                          book={this.state.selectedBook}/>} />
                          <Route path="/books" element={<Books books={this.state.books}
                          onDelete={this.deleteBook}
                          onEdit={this.getBook}
                          onRent={this.rentBook}/>} />
                          <Route path="/" element={<Home />} />
                      </Routes>
                  </div>
              </main>
          </Router>
      );

  }

    componentDidMount() {
    this.fetchData()
    }

    fetchData = () => {
    this.loadCountries();
    this.loadAuthors();
    this.loadBooks();
    this.loadCategories();
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadCountries = () => {
    LibraryService.fetchCountries()
        .then((data) => {
          this.setState({
            countries: data.data
          })
        });
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    addCountry = (name, continent) => {
      LibraryService.addCountry(name, continent).then(() => {
          this.loadCountries();
      });
    }

    addCategory = (name) => {
        LibraryService.addCategory(name).then(() => {
            this.loadCategories();
        });
    }

    addAuthor = (firstname, lastname, countryId) => {
        LibraryService.addAuthor(firstname, lastname, countryId).then(() => {
            this.loadAuthors();
        });
    }

    addBook = (name, category, authorId, availableCopies) => {
        LibraryService.addBook(name, category, authorId, availableCopies).then(() => {
            this.loadBooks();
        });
    }

    deleteBook = (id) => {
      LibraryService.deleteBook(id).then(() => {
         this.loadBooks();
      });
    }

    editBook = (id, name, category, authorId, availableCopies) => {
        LibraryService.editBook(id, name, category, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })

    }

    rentBook = (id) => {
        LibraryService.rentBook(id)
            .then(() => {
                this.loadBooks();
            });
    }


}


export default App;
