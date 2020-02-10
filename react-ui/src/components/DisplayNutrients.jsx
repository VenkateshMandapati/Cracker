import React, {Component} from 'react'

class DisplayNutrients extends Component {

    render(){
        debugger
        console.log("nutrients is " + this.props.totalFoodInfo)
        return (
            this.props.totalFoodInfo!==undefined && 
            <div className="container-fluid">
                <p>Food Name is <i>{this.props.totalFoodInfo.foodName}</i>, measured value is <i>{this.props.totalFoodInfo.measuredFoodWeight}</i> grams</p>
                <table className="table">
                    <thead>
                        <tr>
                            <th>nutrientName</th>
                            <th>value</th>
                            <th>unit</th>
                            <th>hundredGramEquivalentValue</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.props.totalFoodInfo.nutrientsList.map(
                                nutrient =>
                                (
                                    <tr>
                                        <td>{nutrient.nutrientName}</td>
                                        <td>{nutrient.value}</td>
                                        <td>{nutrient.unit}</td>
                                        <td>{nutrient.hundredGramEquivalentValue}</td>
                                    </tr>
                                ) 
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }

}
export default DisplayNutrients