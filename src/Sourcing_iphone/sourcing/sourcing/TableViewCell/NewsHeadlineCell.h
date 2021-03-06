//
//  NewsHeadlineCell.h
//  sourcing
//
//  Created by wuzhu on 13-1-8.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "StyledTableViewCell.h"
#import "NewsVO.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface NewsHeadlineCell : StyledTableViewCell
{  
    NewsVO *_dataVO;
}

@property (nonatomic,setter = setData:) NewsVO *dataVO;


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods

@property (weak, nonatomic) IBOutlet UILabel *laTitle;
@property (weak, nonatomic) IBOutlet UIImageView *ivTitlePic;

@end
