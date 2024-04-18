import React from "react";
import ReactPaginate from "react-paginate";
import AuthorTerm from "./authorTerm";



class Authors extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 5
        }
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.authors.length / this.state.size);
        const authors = this.getAuthorsPage(offset, nextPageOffset);
        console.log(authors, pageCount)
        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Lastname</th>
                                <th scope={"col"}>Country</th>
                            </tr>
                            </thead>
                            <tbody>
                            {authors}
                            </tbody>
                        </table>
                    </div>
                </div>
                <a href={"/author/add"}>Add new author</a>

                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>
            </div>

        );
    }
    handlePageClick = (data) => {
        let selected = data.selected;
        console.log(selected)
        this.setState({
            page: selected
        })
    }

    getAuthorsPage = (offset, nextPageOffset) => {
        console.log(offset, nextPageOffset)
        return this.props.authors.map((term, index) => {
            return (
                <AuthorTerm term={term}/>
            );
        }).filter((author, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}



export default Authors;
