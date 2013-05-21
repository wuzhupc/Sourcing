//
//  CompanyVO.h
//  sourcing
//
//  Created by wuzhu_mac on 13-5-21.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "BaseServiceDelegate.h"

#define CINT_TAG_GETCOMPANYDETAIL 10050

@interface CompanyVO : BaseVO<BaseServiceDelegate>

@property (nonatomic,strong)NSString *companyname;
@property (nonatomic,strong)NSString *industry;
@property (nonatomic,strong)NSString *publishtime;

-(void)setCompanyid:(NSInteger )kid;
-(NSInteger )Companyid;

@end
