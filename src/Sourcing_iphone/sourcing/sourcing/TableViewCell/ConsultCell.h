//
//  ConsultCell.h
//  sourcing
//
//  Created by wuzhu on 13-1-12.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "StyledTableViewCell.h"
#import "BaseVO.h"
#import "RadiusView.h"
#import "OHAttributedLabel.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface ConsultCell : StyledTableViewCell


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties
@property (nonatomic,readonly) BaseVO *dataVO;
@property (weak, nonatomic) IBOutlet RadiusView *radiusViewResult;
@property (weak, nonatomic) IBOutlet OHAttributedLabel *labelResultTitle;
@property (weak, nonatomic) IBOutlet UILabel *labelResultPublishInfo;
@property (weak, nonatomic) IBOutlet OHAttributedLabel *labelTitle;
@property (weak, nonatomic) IBOutlet UILabel *labelStatus;
@property (weak, nonatomic) IBOutlet UILabel *labelPublishtime;


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods
+(CGFloat)calCellHeight:(BaseVO *)kvo;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods
-(void)setData:(BaseVO *)kvo;


@end
