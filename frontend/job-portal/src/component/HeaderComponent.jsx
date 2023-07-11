import './css/Color.css'

function HeaderComponent() {
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-light custom-purple-color">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">JobPortal</a>
                    <div className="justify-content-center">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <a className="nav-link" aria-current="page" href="#">Jobs</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Join Community</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </>
    )
}


export default HeaderComponent; 