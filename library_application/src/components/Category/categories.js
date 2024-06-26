import React from "react";

const categories = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.categories.map((term) => {
                            return (
                                <tr>
                                    <td>{term.name}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
            <a href={"/category/add"}>Add new category</a>
        </div>

    );
}

export default categories;
