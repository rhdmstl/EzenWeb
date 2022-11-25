import React from 'react';
import Book from './book';

function Library() {
    return (
        <div>
            <Book name= "처음 만난 파이썬" numOfPage={300}/>
        </div>
    )
}
export default Library;