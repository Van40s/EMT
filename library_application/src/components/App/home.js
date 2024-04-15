import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
    return (
        <div>
            <h1>Hello, welcome to the library app!</h1>
            <p>Here are some useful links:</p>
            <ul>
                <li><Link to="/countries">Countries</Link></li>
                <li><a href="/authors">Authors</a></li>
                <li><a href="/books">Books</a></li>
                <li><a href="/categories">Categories</a></li>
            </ul>
        </div>
    );
}

export default Home;
