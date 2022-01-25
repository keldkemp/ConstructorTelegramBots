import React from "react";

export const BotsList = () => {

    let listBots;

    return (
        <div className="row">
            <div className="col s12">
                <div className="card grey lighten-4">
                    <div className="card-content black-text">
                        <span className="card-title" style={{fontWeight: "bold"}}>Мои боты</span>
                        <table className="highlight">
                            <thead>
                            <tr>
                                <th width="30%">Наименование</th>
                                <th width="30%">Токен</th>
                                <th width="10%">Статус</th>
                                <th width="40%">Кнопки</th>
                            </tr>
                            </thead>
                            <tbody>
                            {listBots}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    )
}