import React from 'react';

const BookTerm = (props) => {

    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.surname}</td>
            <td>{props.term.country.name}</td>
        </tr>
    );

}

export default BookTerm;