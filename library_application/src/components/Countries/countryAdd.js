import React from 'react';
import {useNavigate} from "react-router-dom";

const CountryAdd = (props) => {
    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        continent: ""
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
        const continent = formData.continent;

        props.addCountry(name, continent);
        history("/countries");
    }


    return (
        <form onSubmit={onFormSubmit}>
            <div>
                <label htmlFor="name">Country name</label>
                <input type="text"
                       className="form-control"
                       id="name"
                       name="name"
                       required
                       placeholder="Enter country name"
                       onChange={handleChange}
                />
            </div>
            <div>
                <label htmlFor="continent">Continent name</label>
                <input type="text"
                       className="form-control"
                       id="continent"
                       name="continent"
                       required
                       placeholder="Enter continent name"
                       onChange={handleChange}
                />
            </div>
            <button id="submit" type="submit" className="btn btn-primary">Submit</button>
        </form>
    );
};

export default CountryAdd;
