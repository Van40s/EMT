import React from 'react';
import {Link} from 'react-router-dom';

const BookTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category.name}</td>
            <td>{props.term.author.name}</td>
            <td>{props.term.availableCopies}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
                <button
                    title={props.term.rented ? "Unavailable" : "Rent"}
                    className={`btn ${props.term.rented ? "btn-secondary" : "btn-danger"}`}
                    onClick={() => !props.term.rented && props.onRent(props.term.id)}
                    disabled={props.term.rented}>
                    {props.term.rented ? "Unavailable" : "Rent"}
                </button>
            </td>

        </tr>
    )
}

export default BookTerm;
