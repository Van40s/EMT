import React from 'react';
import {useNavigate} from "react-router-dom";

const BookAdd = (props) => {
    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: 1,
        authorId: 1,
        availableCopies: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim(),
        })
    }


    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name;
        const category = formData.category;
        const authorId = formData.authorId;
        const availableCopies = formData.availableCopies;
        props.addBook(name, category, authorId, availableCopies);
        history("/books");
    }


    return (
        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="name">Book name</label>
                <input type="text"
                       className="form-control"
                       id="name"
                       name="name"
                       required
                       placeholder="Enter book name"
                       onChange={handleChange}
                />
            </div>
            <div>
                <label>Category</label>
                <select name="category" className="form-control" onChange={handleChange}>
                    {props.categories.map((term) =>
                        <option value={term.id}>{term.name}</option>
                    )}
                </select>
            </div>
            <div className="form-group">
                <label>Author</label>
                <select name="authorId" className="form-control" onChange={handleChange}>
                    {props.authors.map((term) =>
                        <option value={term.id}>{term.name}</option>
                    )}
                </select>
            </div>
            <div>
                <label htmlFor="availableCopies">Available copies</label>
                <input type="number"
                       className="form-control"
                       id="availableCopies"
                       name="availableCopies"
                       required
                       placeholder="Enter available copies"
                       onChange={handleChange}
                />
            </div>
            <button id="submit" type="submit" className="btn btn-primary">Submit</button>
        </form>
    );
};

export default BookAdd;
