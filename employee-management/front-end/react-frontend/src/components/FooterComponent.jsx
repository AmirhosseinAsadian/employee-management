import React, { Component } from 'react';

class FooterComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
    }

    render() {
        return (
            <div>
                <footer className='footer'>
                    <span className='text-muted'>کایه حقوق این برنامه متعلق به امیرحسین اسدیان می باشد</span>
                </footer>
            </div>
        );
    }
}

export default FooterComponent;