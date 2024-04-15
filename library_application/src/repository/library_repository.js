import axios from "../custom-axios/axios";

const LibraryService = {
    fetchCountries: () => {
        return axios.get("/country/getAll");
    },
    fetchAuthors: () => {
        return axios.get("/author/getAll");
    },
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchCategories: () => {
        return axios.get("/categories/getAll");
    },

    addCountry: (name, continent) => {
        const url = `/country?name=${name}&continent=${continent}`;
        return axios.get(url);
    },
    addCategory: (name) => {
        const url = `/categories?name=${name}`;
        return axios.get(url);
    },

    addAuthor: (firstname, lastname, countryId) => {
        return axios.post("/author/add-author", {
            "firstname": firstname,
            "lastname": lastname,
            "countryId": countryId
        });
    },
    addBook: (name, category, authorId, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "categoryId": category,
            "authorId": authorId,
            "availableCopies": availableCopies
        });
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete-book/${id}`)
    },

    editBook: (id, name, category, authorId, availableCopies) => {
        return axios.put(`/books/edit-book/${id}`, {
            "name": name,
            "categoryId": category,
            "authorId": authorId,
            "availableCopies": availableCopies
        });
    },

    getBook: (id) => {
        return axios.get(`/books/getBook/${id}`);
    },

    rentBook: (id) => {
        return axios.put(`/books/mark-rented/${id}`, {
            "id": id
        })
    }
}

export default LibraryService;