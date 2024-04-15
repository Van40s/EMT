import React from 'react';
import {useNavigate} from "react-router-dom";

const AuthorAdd = (props) => {
    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        firstname: "",
        lastname: "",
        countryId: 1
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim(),
    })
    }


    const onFormSubmit = (e) => {
        e.preventDefault();
        const firstname = formData.firstname;
        const lastname = formData.lastname;
        const countryId = formData.countryId;
        props.addAuthor(firstname, lastname, countryId);
        history("/authors");
    }


    return (
        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="name">Country name</label>
                <input type="text"
                       className="form-control"
                       id="firstname"
                       name="firstname"
                       required
                       placeholder="Enter firstname"
                       onChange={handleChange}
                />
            </div>
            <div>
                <label htmlFor="continent">Continent name</label>
                <input type="text"
                       className="form-control"
                       id="lastname"
                       name="lastname"
                       required
                       placeholder="Enter lastname"
                       onChange={handleChange}
                />
            </div>
            <div className="form-group">
                <label>Country</label>
                <select name="countryId" className="form-control" onChange={handleChange}>
                    {props.countries.map((term) =>
                        <option value={term.id}>{term.name}</option>
                    )}
                </select>
            </div>

            <button id="submit" type="submit" className="btn btn-primary">Submit</button>
        </form>
    );
};

export default AuthorAdd;
