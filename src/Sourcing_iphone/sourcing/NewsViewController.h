//
//  NewsViewController.h
//  sourcing
//
//  Created by wuzhu on 13-1-2.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import <UIKit/UIKit.h>
#import "BaseHomeViewController.h"
#import "BrowserTabView.h"
#import "ChannelVO.h"
#import "PullingRefreshTableView.h"
#import "BaseServiceDelegate.h"


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

#define CINT_TAG_LOADNEWDATA 10001
#define CINT_TAG_LOADMOREDATA 10002

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface NewsViewController : BaseHomeViewController<BrowserTabViewDelegate,PullingRefreshTableViewDelegate,UITableViewDataSource,UITableViewDelegate,BaseServiceDelegate>
{
    BrowserTabView *subChannelBTV;
    NSArray *subChannels;
    PullingRefreshTableView *prTableView;
    NSInteger nowSelChannel;
    NSMutableArray *newslist;
}


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Outlets
@property (weak, nonatomic) IBOutlet UIScrollView *subChannelScrollView;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions


@end
