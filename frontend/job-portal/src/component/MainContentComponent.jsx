import './css/Color.css'
function MainContentComponent(){
    return (
        <>
        <div className="container">
            <div className="align-middle text-start mt-5 pt-5">
            <p className="fw-light display-3 custom-purple-color-text">Discover your dream job with our comprehensive job search portal.</p>
            </div>
            <div className="text-start mt-5">
                <p className="fs-3 text-muted fw-light">Sign Up as...</p>
            </div>
            <div className="text-start mt-2">
            <button className="btn btn-secondary">JobSeeker</button>
            <button className="btn btn-secondary m-1">Employer</button>
            </div>
        </div>
        </>
    )
}

export default MainContentComponent;