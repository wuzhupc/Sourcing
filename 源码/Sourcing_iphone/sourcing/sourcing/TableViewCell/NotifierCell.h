//
//  NotifierCell.h
//  sourcing
//
//  Created by wuzhu on 13-1-12.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "StyledTableViewCell.h"
#import "NotifierVO.h"
#import "OHAttributedLabel.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface NotifierCell : StyledTableViewCell


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties
@property (weak, nonatomic) IBOutlet OHAttributedLabel *labelNotifier;
@property (weak, nonatomic) IBOutlet UILabel *labelPublisher;
@property (weak, nonatomic) IBOutlet UILabel *labelPublishtime;
@property (nonatomic,readonly) NotifierVO *dataVO;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods
+(CGFloat)calCellHeight:(NotifierVO *)kvo;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods
-(void)setData:(NotifierVO *)kvo;

@end
